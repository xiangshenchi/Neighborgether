import { reactive } from 'vue'
import { loadFull } from 'tsparticles'
import type { Engine, Container, ISourceOptions } from 'tsparticles-engine'


export function useParticles() {
  // 初始化粒子效果
  const particlesInit = async (engine: Engine) => {
    await loadFull(engine)
  }

  // 粒子效果加载完成后的处理
  const particlesLoaded = async (container: Container | undefined) => {
    console.log('Particles container loaded', container)
  }

  // 粒子效果的配置选项
  const options = reactive<ISourceOptions>({
    background: {
      color: {
        value: 'rgb(239, 239, 239)'
      }
    },
    fpsLimit: 60,
    interactivity: {
      events: {
        onClick: {
          enable: true,
          mode: 'push'
        },
        onHover: {
          enable: true,
          mode: 'grab'
        },
        resize: true
      },
      modes: {
        bubble: {
          distance: 400,
          duration: 2,
          opacity: 0.8,
          size: 40
        },
        push: {
          quantity: 4
        },
        repulse: {
          distance: 200,
          duration: 0.4
        }
      }
    },
    particles: {
      color: {
        value: '#555'
      },
      links: {
        color: '#555',
        distance: 150,
        enable: true,
        opacity: 0.4,
        width: 1
      },
      collisions: {
        enable: false
      },
      move: {
        direction: 'none',
        enable: true,
        outModes: {
          default: 'bounce'
        },
        random: false,
        speed: 4,
        straight: false
      },
      number: {
        density: {
          enable: true,
          area: 800
        },
        value: 80
      },
      opacity: {
        value: 0.5
      },
      shape: {
        type: 'circle'
      },
      size: {
        random: true,
        value: 5
      }
    },
    detectRetina: true
  })

  return {
    particlesInit,
    particlesLoaded,
    options
  }
}
